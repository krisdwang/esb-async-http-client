package com.primeton.tip.endpoint.ws.async.httpclient.protocol;

public class HeaderElement<K, V> {
	private final K key;
	private final V value;

	public HeaderElement(K k, V v) {
		this.key = k;
		this.value = v;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

	public String toString() {
		return "Pair(key: " + key + ", value: " + value + ")";
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !o.getClass().equals(this.getClass()))
			return false;

		final HeaderElement<?, ?> pair = (HeaderElement<?, ?>) o;

		return (key != null ? key.equals(pair.key) : pair.key == null)
				&& (value != null ? value.equals(pair.value)
						: pair.value == null);
	}

	public int hasCode() {
		int result;
		result = (key != null ? key.hashCode() : 0);
		result = 29 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
}
