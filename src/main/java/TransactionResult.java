import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class TransactionResult
{
    private String tag_4F; //
    private String tag_50; //
    private String tag_5F2A; //
    private String tag_82; //
    private String tag_95; //
    private String tag_9A; //
    private String tag_9B; //
    private String tag_9C; //
    private String tag_9F02; //
    private String tag_9F03; //
    private String tag_9F0D; //
    private String tag_9F0E; //
    private String tag_9F0F; //
    private String tag_9F10; //
    private String tag_9F26; //
    private String tag_9F27; //
    private String tag_9F1A; //
    private String tag_9F34; //
    private String tag_9F36; //
    private String tag_9F37; //
    private String tag_9F5D; //
    private String tag_DF01; //
    private String tag_DF02; //
    private String tag_DF03;  //
    private String tag_DF04;  //
    private String tag_DF05;  //
    private String tag_DF07;  //
    private String tag_DF08;  //
    private String tag_DF09;  //
    private String tag_DF0C;  //
    private String tag_DF50;  //
    private String tag_DF51;  //
    private String tag_DF52;  //
    private String tag_DF53;  //
    private String tag_DF55;  //
    private String tag_DF56;  //
    private String tag_DF57;  //
    private String tag_DF58;  //
    private String tag_DF59;  //
    private String tag_DF90;  //
    private String tag_DF91;  //
    private String tag_DF92;  //
    private String tag_DF93;  //
    private String tag_DF94;  //
    private String tag_DF95;  //
    private String tag_DFFC;  //
    private String tag_DFFD;  //
    private String tag_DFFE;  //
    private String tag_DFFF;  //

    private final Set<String> anSet = new HashSet<>();

    private void set_anSet()
    {
        anSet.add("50");
        anSet.add("DF04");
        anSet.add("DF09");
        anSet.add("DF50");
        anSet.add("DF51");
        anSet.add("DF52");
        anSet.add("DF53");
        anSet.add("DF55");
        anSet.add("DF56");
        anSet.add("DF58");
        anSet.add("DF59");
        anSet.add("DF92");
        anSet.add("DF94");
    }

    public TransactionResult (byte[] Frame54, byte[] Frame51) throws NoSuchFieldException, IllegalAccessException
    {
        Set<Tags.DLVField> DLVFields54 = new Tags(Frame54).DLVFields;
        Set<Tags.DLVField> DLVFields51 = new Tags(Frame51).DLVFields;
        set_anSet();

        for (Tags.DLVField field:DLVFields54)
        {
            String fdata =field.data;
            Field F = this.getClass().getDeclaredField("tag_" + field.tag);
            if ( anSet.contains(field.tag) )
            {
                byte[] bArr = new byte[fdata.length()/2];
                for (int j = 0; j < fdata.length(); j += 2)
                {
                    char ch = (char)Integer.parseInt(field.data.substring( j, j+2), 16);
                    bArr[j/2] = (byte) ch;
                }
                fdata = new String(bArr);
            }
             F.set(this,fdata);
        }

        for (Tags.DLVField field:DLVFields51)
        {
            String fdata =field.data;
            Field F = this.getClass().getDeclaredField("tag_" + field.tag);
            if ( anSet.contains(field.tag) )
            {
                byte[] bArr = new byte[fdata.length()/2];
                for (int j = 0; j < fdata.length(); j += 2)
                {
                    char ch = (char)Integer.parseInt(field.data.substring( j, j+2), 16);
                    bArr[j/2] = (byte) ch;
                }
                fdata = new String(bArr);
            }
            F.set(this,fdata);
        }

        for(Field fld: this.getClass().getDeclaredFields())
        {

            //System.out.println(fld.get(this));
        }


    } // constructor

    public String getDF01() {
        return tag_DF01;
    }

    public String getDF02() {
        return tag_DF02;
    }

    public String getDF03() {
        return tag_DF03;
    }

    public String getDF04() {
        return tag_DF04;
    }

    public String getDF07() {
        return tag_DF07;
    }

    public String getDF08() {
        return tag_DF08;
    }

    public String getDF09() {
        return tag_DF09;
    }

    public String getDF50() {
        return tag_DF50;
    }

    public String getDF51() {
        return tag_DF51;
    }

    public String getDF52() {
        return tag_DF52;
    }

    public String getDF53() {
        return tag_DF53;
    }

    public String getDF55() {
        return tag_DF55;
    }

    public String getDF56() {
        return tag_DF56;
    }

    public String toString()
    {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
} // class
