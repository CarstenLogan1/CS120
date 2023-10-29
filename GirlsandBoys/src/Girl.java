public class Girl {

//--------------------------------------------------------------

    private Girl bestFriend;

    private Boy boyFriend;

//--------------------------------------------------------------

    public Girl(Girl newBestFriend) {



        bestFriend = newBestFriend;

        boyFriend = null;

    }

//--------------------------------------------------------------

    public void addBoyFriend(Boy friend) {


        boyFriend = friend;

    }

//--------------------------------------------------------------

}
