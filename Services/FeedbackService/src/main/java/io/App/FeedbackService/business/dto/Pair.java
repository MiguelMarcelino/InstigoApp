package io.App.FeedbackService.business.dto;

import java.io.Serializable;

public class Pair<T1, T2> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T1 fst;
	private T2 snd;

	/**
	 * Creates a pair of generic Objects
	 * 
	 * @param fst
	 *            - first element of the pair
	 * @param snd
	 *            - second element of the pair
	 */
	public Pair(T1 fst, T2 snd) {
		this.fst = fst;
		this.snd = snd;
	}

	/**
	 * Gets the first element of the pair
	 * 
	 * @return first element
	 */
	public T1 getFirst() {
		return fst;
	}

	/**
	 * Gets the second element of the pair
	 * 
	 * @return second element
	 */
	public T2 getSecond() {
		return snd;
	}

	/**
	 * Returns a sting representation of the pair
	 */
	public String toString() {
		return this.fst + "\n" + this.snd;
	}
}