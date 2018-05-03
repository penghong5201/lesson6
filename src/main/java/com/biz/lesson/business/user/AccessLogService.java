package com.biz.lesson.business.user;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.cache.ConfigCacheAdapter;
import com.biz.lesson.dao.user.AccessLogRepository;
import com.biz.lesson.model.config.Config;
import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.util.SimpleTimerDataLoader;
import com.biz.lesson.vo.user.AccessLogSearchVo;
/**
 * 用来记录 访问日志
 */
@Service("accessLogService")
public class AccessLogService extends BaseService {

	@PostConstruct
	public void setup(){

		//每一秒钟从ACCESS_LOG_STORAGE中拿出最多一千条访问日志出来，批量存入数据库
		new SimpleTimerDataLoader("AccessLogDumper", 0, 1, TimeUnit.SECONDS, false) {

			@Override
			protected Object loadDataByTimerTask() {

				if(!ACCESS_LOG_STORAGE.isEmpty()){
					Integer count = 0;
					List<AccessLogPo> accessLogPos = newArrayList();
					while (!ACCESS_LOG_STORAGE.isEmpty()){
						accessLogPos.add(ACCESS_LOG_STORAGE.poll());
						if(++count >= logCountInOneBatch){
							break;
						}
					}
					accessLogRepository.save(accessLogPos);
					if(logger.isDebugEnabled()){
						logger.debug("Success save {} access logs to db.", accessLogPos.size());
					}
				}
				return null;
			}
		};
	}
	
	public Page<AccessLogPo> searchAccessLog(AccessLogSearchVo vo,Pageable pageable){
		return accessLogRepository.search(vo, pageable);
	}

	public void saveLog(AccessLogPo accessLog) {

		if(logger.isTraceEnabled()){
			logger.trace("save log to storage queue {}", accessLog);
		}
		if(saveLogSync()){
			accessLogRepository.save(accessLog);
		} else {
			ACCESS_LOG_STORAGE.add(accessLog);
		}
	}

	private Boolean saveLogSync(){

		Config config = configCacheAdapter.getConfig();
		if(config == null){
			return true;
		} else {
			return config.getAccessLogSaveSync();
		}
	}

	@Autowired
	private ConfigCacheAdapter configCacheAdapter;

	@Autowired
	private AccessLogRepository accessLogRepository;

	private static final Integer logCountInOneBatch = 1000;

	private static final Logger logger = LoggerFactory.getLogger(AccessLogService.class);

	private static final ConcurrentLinkedQueue<AccessLogPo> ACCESS_LOG_STORAGE = new ConcurrentLinkedQueue<>();

}
