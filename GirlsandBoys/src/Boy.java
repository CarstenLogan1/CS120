public class Boy {

//--------------------------------------------------------------

    private Girl friend;

//--------------------------------------------------------------

    public Boy(Girl lovesYou) {



        if (lovesYou == null) {

            friend = new Girl(null);

        } else {

            friend = lovesYou;

        }

    }

//--------------------------------------------------------------

}