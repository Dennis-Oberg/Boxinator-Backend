package com.Dennis.Boxinator;

import com.Dennis.Boxinator.Controller.BoxController;
import com.Dennis.Boxinator.Model.Box;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BoxinatorApplicationTests {

    @Autowired
    private BoxController boxController;

    @Test
    public void returnChinaCost() {
        Box b = new Box();
        b.setCountry("China");
        b.setWeightInKiloGrams(1000);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
        Assertions.assertEquals(4000, b.getShippingCost());
    }

    @Test
    public void returnBrazilCost() {
        Box b = new Box();
        b.setCountry("Brazil");
        b.setWeightInKiloGrams(300);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
        Assertions.assertEquals(2580, b.getShippingCost());
    }

    @Test
    public void okResponseOnBadFormattingOfCountry() {
        Box b = new Box();
        b.setCountry("bRazIl");
        b.setWeightInKiloGrams(300);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
        Assertions.assertEquals(2580, b.getShippingCost());
    }

    @Test
    public void returnAustraliaCost() {
        Box b = new Box();
        b.setCountry("australia");
        b.setWeightInKiloGrams(1000d);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
        Assertions.assertEquals(7200, b.getShippingCost());
    }

    @Test
    public void testCalculateCostMethodInConstructor(){
        Box b = new Box("Sample"
                , "australia"
                , 1000
                ,"#fff");

        Assertions.assertEquals(7200, b.getShippingCost());
    }

    @Test
    public void returnCostSweden() {
        Box b = new Box();
        b.setCountry("sweden");
        b.setWeightInKiloGrams(300);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
        Assertions.assertEquals(390, b.getShippingCost());
    }

    @Test
    public void badResponseIfCostIsNegative() {
        Box b = new Box();
        b.setCountry("sweden");
        b.setWeightInKiloGrams(-300);
        Assertions.assertFalse(b.validateWeigth(b.getWeightInKiloGrams()));
    }

    @Test
    public void setShippingCostCalc() {
        Box b = new Box();
        b.setId(1);
        b.setWeightInKiloGrams(300);
        b.setCountry("Sweden");
        b.setContainerColour("#ffff");
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));

        Assertions.assertEquals(390, b.getShippingCost());
    }

    @Test
    public void checkCalculateShippingsCostWhenCountryIsNotInSwitch() {
        Box b = new Box();
        b.setCountry("germany");

        Assertions.assertThrows(NullPointerException.class, () -> b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));
    }

    @Test
    public void setShippingCost() {
        Box b = new Box();
        b.setCountry("australia");
        b.setWeightInKiloGrams(1000);
        b.setShippingCost(b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()));

        Assertions.assertEquals(7200, b.getShippingCost());
    }

    @Test
    void contextLoads() {
        assertThat(boxController).isNotNull();
    }

    @Test
    public void assertInsertAmountRound() {
        Box b = new Box();
        b.setCountry("china");
        b.setWeightInKiloGrams(12893.233);

        double cost = b.calculateShippingCostBasedOnCountry(b.getWeightInKiloGrams()); // 16761.199
        b.setShippingCost(cost); //ain't making a one-liner for readability

        double c = b.roundToTwoDecimals(b.getShippingCost());

        Assertions.assertEquals(51572.93, c);
    }

    @Test
    public void assertBoxNotNull() {
        Box b = new Box();
        Assertions.assertNotNull(b);
    }
}
