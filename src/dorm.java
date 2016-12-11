import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Каролина on 08.12.2016.
 */
public class dorm {

    public static String Waybill(){

        String string = "select * from waybill";

        return string;
    }

    public static String createConsignee(){
        String string = "select * from consignee";

        return string;
    }

    public static String infOrganization(){
        String string = "select * from inforganization";

        return string;
    }

    public static String infProduct(){
        String string = "select * from infproduct";

        return string;
    }

    public static String infPayer(){
        String string = "select * from infpayer";

        return string;
    }

    public static String infSupply(){
        String string = "select * from infsupply";

        return string;
    }

    public static String bankDetails(){
        String string = "select * from bankdetails";

        return string;
    }

    public static String infOfficial(){
        String string = "select * from infofficial";

        return string;
    }

    public static String infManufacturer(){
        String string = "select * from infmanufacturer";

        return string;
    }

    public static String infIssue(){
        String string = "select * from infissue";

        return string;
    }
}
