package edu.wpi.cs.wpisuitetng.network;

import java.util.ArrayList;
import java.util.List;

/**
 * This class exists only to be extended by Request.
 */
public abstract class Observable {
	private List<RequestObserver> observers;

	public Observable() {
		observers = new ArrayList<RequestObserver>();
	}

	/**
	 * Adds a RequestObserver to this Observable.
	 * 
	 * @param o The RequestObserver to add.
	 */
	public void addObserver(RequestObserver o) {
		observers.add(o);
	}
	
	/**
	 * Returns the number of RequestObservers for this Observable.
	 * 
	 * @return The number of RequestObservers for this Observable.
	 */
	public int countObservers() {
		return observers.size();
	}

	/**
	 * Notifies RequestObservers of a response with a status code indicating success (2xx).
	 */
	public void notifyObserversResponseSuccess() {
		for (RequestObserver obs : observers) {
			obs.responseSuccess((IRequest) this);
		}
	}

	/**
	 * Notifies RequestObservers of response with a status code indicating a client error (4xx) for server error (5xx).
	 */
	public void notifyObserversResponseError() {
		for (RequestObserver obs : observers) {
			obs.responseError((IRequest) this);
		}
	}

	/**
	 * Notifies RequestObservers of a failure in sending a request.
	 * 
	 * @param exception An exception.
	 */
	public void notifyObserversFail(Exception exception) {
		for (RequestObserver obs : observers) {
			obs.fail((IRequest) this, exception);
		}
	}
}