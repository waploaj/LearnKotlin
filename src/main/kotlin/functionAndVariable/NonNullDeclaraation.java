package functionAndVariable;

import java.util.List;

//java class without nullabilty annotation
public class NonNullDeclaraation {
    private final String name;

    public NonNullDeclaraation(String name){
        this.name = name;
    }


    public String getName() {

        return name;
    }
}

interface StringProcessor{
   public void process(String value);
}

