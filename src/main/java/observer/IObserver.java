package observer;

import listenersEvents.GeneralEvent;

public interface IObserver {
	void update(GeneralEvent event);

}
