package org.politechnika.matlab;

import org.junit.Assert;
import org.junit.Test;
import org.politechnika.matlab.commons.MatlabCommons;

import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;

public class MatlabFunctionsTest {

    @Test
    public void shouldAddElementToArray() {
        Object[] oldArray = {"abc", "efg"};

        Object[] newArray = MatlabCommons.appendToArray(oldArray, "hij");
        for (Object o : newArray) {
            System.out.println((String) o);
        }

        Assert.assertThat(newArray, hasItemInArray("hij"));
    }
}
