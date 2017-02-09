package com.vmware.io;

public class Pair<F, S> {
	private F first;
	private S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return this.first;
	}

	public S getSecond() {
		return this.second;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other instanceof Pair) {
			Pair<F, S> otherPair = (Pair<F, S>) other;
			if (this.first.equals(otherPair.getFirst())
					&& this.second.equals(otherPair.getSecond())) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.first.hashCode() ^ this.second.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		result.append(this.first);
		result.append(", ");
		result.append(this.second);
		result.append(")");
		return result.toString();
	}
}
