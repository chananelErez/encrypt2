package observer;

import fileOperation.GeneralEvent;

public interface IObserver {
	void update(GeneralEvent event);

}
