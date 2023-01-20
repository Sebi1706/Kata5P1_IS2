package kata5;


public class Mail {
    private String email;

    public Mail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getDomain(){
        String email = getEmail();
        int indexOfAt = email.indexOf("@");
        return email.substring(indexOfAt + 1);
    }
}
