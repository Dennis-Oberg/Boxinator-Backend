package com.Dennis.Boxinator.MockTest;

import com.Dennis.Boxinator.Model.Box;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BoxMockC extends BoxMock {

    @Override
    @Before
    public void setup() {
        super.setup();
    }
    //should have mocked the db.
    @Test
    public void createBox() throws Exception {
        String uri = "/addbox";
        Box box = new Box();
        box.setId(1);
        box.setBoxName("sample");
        box.setWeightInKiloGrams(100);
        box.setContainerColour("#fff");
        box.setCountry("sweden");
        box.setShippingCost(box.calculateShippingCostBasedOnCountry(box.getWeightInKiloGrams()));

        String inputJson = super.mapToJson(box);
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content, "Added Box " + box.getBoxName());
    }

    @Test
    public void failToCreateProductNegativeCost() throws Exception {
        String uri = "/addbox";
        Box box = new Box();
        box.setId(1);
        box.setBoxName("sample");
        box.setWeightInKiloGrams(-100);
        box.setContainerColour("#fff");
        box.setCountry("sweden");
        box.setShippingCost(box.calculateShippingCostBasedOnCountry(box.getWeightInKiloGrams()));

        String inputJson = super.mapToJson(box);
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertNotEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content, "Failed To Add " + box.getBoxName());
    }

    @Test
    public void failToCreateProductBadCountry()  {
        Box box = new Box();
        box.setCountry("usa");

        Assertions.assertThrows(NullPointerException.class, () -> box.calculateShippingCostBasedOnCountry(box.getWeightInKiloGrams()));
    }
}
