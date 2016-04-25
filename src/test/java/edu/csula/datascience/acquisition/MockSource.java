package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import edu.csula.datascience.acquisition.Source;

import java.util.Collection;

/**
 * A mock source to provide data
 */
public class MockSource implements Source<MockData> {
    int index = 0;

    @Override
    public boolean hasNext() {
        return index < 1;
    }

    @Override
    public Collection<MockData> next() {
        return Lists.newArrayList(
            new MockData("1", null, null, null, null, null),
            new MockData("PA", "BUS LANE VIOLATION","5","JTW5438","PAS","1206P"),
            new MockData("NY", "BUS LANE VIOLATION","16","GTH1661","PAS","0134P")
        );
    }
}
