package adService;

import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import writingFormat.IntFormat;

public interface PopularEvents {
	EncryptionEvent EncryptionEnded(IntFormat file);
    EncryptionEvent DecryptionStarted(IntFormat file);
    EncryptionEvent EncryptionStarted(IntFormat file);
    EncryptionEvent DecryptionEnded(IntFormat file);
    ErrorEvent PathNotFound(IntFormat file);
    ErrorEvent InvalidKey(IntFormat file);
}
