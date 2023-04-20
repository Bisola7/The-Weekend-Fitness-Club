public class Review {
    int rating;
    String message;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Review(int rating, String message) {
        this.rating = rating;
        this.message = message;
    }

    public Review(int rating) {
        this.rating = rating;
        this.message = "";
    }
}
