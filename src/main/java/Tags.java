import java.util.HashSet;
import java.util.Set;

public class Tags
{
    public Set<DLVField> DLVFields = new HashSet<>();

    private final Set<String> shortTags = new HashSet<>();

    private void set_shortTags()
    {
        shortTags.add("4F");
        shortTags.add("50");
        shortTags.add("82");
        shortTags.add("95");
        shortTags.add("9A");
        shortTags.add("9B");
        shortTags.add("9C");
    }

  public class DLVField
    {
        String tag;
        String data;
    }

    public Tags (byte[] Frame)
    {
        String stringFrame = new String(Frame);
        int index = 12;
        DLVField field;
        do
            {
                field = getField(stringFrame, index);
                if (field != null)
                {
                    DLVFields.add(field);
                    index += field.tag.length() + field.data.length() + 2;
                }
            } while (field != null);
    }

    private DLVField getField (String strFrame, int index)
    {
       DLVField field = new DLVField();
       set_shortTags();

       if (strFrame.charAt(index) != (byte)3) {return null;}

       String tag = strFrame.substring(index,index+2);
       int lenpos = index + 2;
       if (! shortTags.contains(tag))
       {
           tag = strFrame.substring(index,index+4);
           lenpos += 2;
       }
       int len = Integer.parseInt(strFrame.substring(lenpos,lenpos+2),16) * 2;
       int datapos = lenpos +2;
       String data = strFrame.substring(datapos, datapos+len);

       field.tag = tag;
       field.data = data;

       return field;
    }
    
} // class
