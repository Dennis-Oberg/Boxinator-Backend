package com.Dennis.Boxinator.Service;

import com.Dennis.Boxinator.DBConn.ExecuteSQL;
import com.Dennis.Boxinator.Model.Box;
import com.Dennis.Boxinator.Repository.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService implements BoxInterface {

    private final ExecuteSQL executeSQL = new ExecuteSQL();
    //BoxRepository not used since I created my own methods
    private BoxRepository boxRepository;

    BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    @Override
    public void add(Box box) {

        double initialCost = box.calculateShippingCostBasedOnCountry(box.getWeightInKiloGrams());
        //remove "roundToTwiDecimals()" to obtain the full number
        // , I don't know which one you prefer.
        // Since it´s money we´re working with I assume 2decimal-rounding is nice.
        box.setShippingCost(box.roundToTwoDecimals(initialCost));
        executeSQL.addBox(box); //my own solution
        /*
        or Via the jpareposity ( boxrepository)
        boxRepository.add(box.getContainerColour()
                , box.getCountry()
                , box.getBoxName()
                , box.getShippingCost()
                , box.getWeightInKiloGrams());
         */
    }

    @Override
    public List<Box> getBoxes() {
        return executeSQL.listboxes(); //my own solution
        //return boxRepository.findAll(); Via the jpareposity ( boxrepository)
    }
}
