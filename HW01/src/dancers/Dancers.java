package dancers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.TreeMap;

public class Dancers implements IDancers {

    TreeMap<DancerKey, Dancer> tree = new TreeMap<>();

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer d) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        // TODO Auto-generated method stub
        return null;
    }

}