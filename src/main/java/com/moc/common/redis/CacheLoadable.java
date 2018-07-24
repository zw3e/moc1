package com.moc.common.redis;

public interface CacheLoadable<T> {

	//默认就是public
	 T load();
}
