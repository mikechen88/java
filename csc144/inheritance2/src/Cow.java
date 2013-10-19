// Definition of the Cow class
// A Cow is a Bovine that makes specific noises

public class Cow extends Bovine
{
  // Constructor
  public Cow()
  {
	  super( "A cow is a bovine that is jealous of the movie star status of buffalos");
  }

  // We need to override speak if we want to be able to
  // instantiate a Buffalo
  public void speak()
  {
	  System.out.println("Help me! some kid just tipped me.");
  }

@Override
public double getPrice() {
	// TODO Auto-generated method stub
	return 1000;
}
}

