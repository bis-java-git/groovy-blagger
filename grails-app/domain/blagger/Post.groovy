package blagger

class Post {

    String title

    String email

    String content

    String category

    Date dateCreated

    Date lastUpdated

    static constraints = {
        title minSize: 3, blank: false
        content blank: false
        email email: true, blank: false
        category blank: false
    }

    static mapping = {
        content type: 'text'
    }

}
