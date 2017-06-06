package org.jpmilhau.maps.ordered;

public final class KeyPair<K> {
	
	private final K key;
	private final K nextKey;
	
	public KeyPair(K key, K nextKey) {
		this.key = key;
		this.nextKey = nextKey;
	}

	public K getKey() {
		return key;
	}

	public K getNextKey() {
		return nextKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((nextKey == null) ? 0 : nextKey.hashCode());
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
		KeyPair<?> other = (KeyPair<?>) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "KeyPair [key=" + key + ", nextKey=" + nextKey + "]";
	}
	
}
