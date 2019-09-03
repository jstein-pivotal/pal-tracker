package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<String, TimeEntry> store = new HashMap<String, TimeEntry>();
    private long nextId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.id = nextId;
        store.put(Long.toString(nextId), timeEntry);
        nextId++;
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return store.get(Long.toString(id));
    }

    public List<TimeEntry> list() {
        ArrayList<TimeEntry> arrayList = new ArrayList<TimeEntry>();
        for (long i = 1; i < store.size() + 1; i++) {
            arrayList.add(store.get(Long.toString(i)));
        }
        return arrayList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (! store.containsKey(Long.toString(id)))
            return null;
        timeEntry.id = id;
        store.put(Long.toString(id), timeEntry);
        return timeEntry;
    }

    public void delete(long id) {
        store.remove(Long.toString(id));
    }
}
