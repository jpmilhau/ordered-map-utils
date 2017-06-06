package org.jpmilhau.maps.ordered;

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
