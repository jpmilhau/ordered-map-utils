package com.jpmilhau.maps.ordered;

/*-
 * #%L
 * OrderedMapUtils
 * %%
 * Copyright (C) 2016 - 2019 Jean-Pierre Milhau
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * Class to store the result of an operation applied to the values of a key and its next key in a ordered map
 * 
 * @author Jean-Pierre Milhau
 *
 * @param <K> the type of a key
 * @param <R> the type of the result of the operation
 */
public final class Operation<K, R> {
	
	private final K key;
	private final K nextKey;
	private final R result;
	
	public Operation(K key, K nextKey, R result) {
		this.key = key;
		this.nextKey = nextKey;
		this.result = result;
	}

	public K getKey() {
		return key;
	}

	public K getNextKey() {
		return nextKey;
	}

	public R getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((nextKey == null) ? 0 : nextKey.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation<?, ?> other = (Operation<?, ?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (nextKey == null) {
			if (other.nextKey != null)
				return false;
		} else if (!nextKey.equals(other.nextKey))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operation [key=" + key + ", nextKey=" + nextKey + ", result=" + result + "]";
	}

	

	
}
