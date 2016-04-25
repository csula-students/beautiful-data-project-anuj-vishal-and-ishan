package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import edu.csula.datascience.acquisition.Collector;
import edu.csula.datascience.acquisition.Source;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


/**
 * A test case to show how to use Collector and Source
 */
public class CollectorTest {
    private Collector<SimpleModel, MockData> collector;
    private Source<MockData> source;

    @Before
    public void setup() {
        collector = new MockCollector();
        source = new MockSource();
    }

    @Test
    public void mungee() throws Exception {
        List<SimpleModel> list = (List<SimpleModel>) collector.mungee(source.next());
        
        for(SimpleModel temp:list){
        	
        	System.out.println("list"+temp);
        }
        List<SimpleModel> expectedList = Lists.newArrayList(
            new SimpleModel("PA", "BUS LANE VIOLATION","5","JTW5438","PAS","1206P"),
            new SimpleModel("NY", "BUS LANE VIOLATION","16","GTH1661","PAS","0134P")
        );

        
        for (int i = 0; i < 2; i ++) {
            Assert.assertEquals(list.get(i).getDescrption(), expectedList.get(i).getDescrption());
            Assert.assertEquals(list.get(i).getPlate_id(), expectedList.get(i).getPlate_id());
        }
    }
}