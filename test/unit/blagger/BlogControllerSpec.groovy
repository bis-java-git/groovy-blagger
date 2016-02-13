package blagger

import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(BlogController)
@Mock(Post)
class BlogControllerSpec extends spock.lang.Specification {

    def 'List returns the blag posts'(){

        setup:
            new Post(title: 'test', email: 'test@test.com', content: 'test', category: 'test').save()

        and:
            def model = controller.list()

        expect:
            model.posts
        'test' == model.posts[0].title
    }


     def 'List returns the blag posts in the correct order'(){
    
            setup:
                new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'Sport').save()
                new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'Electric').save()
    
            and:
                def model = controller.list()
    
            expect:
                model.posts
                'test2' == model.posts[0].title
                'Electric' == model.posts[0].category
                'test1' == model.posts[1].title
                'Sport' == model.posts[1].category
    }
    
    def 'List returns the blag posts maximum 5 items per page'(){
    
            setup:
            new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test3', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test4', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test5', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test6', email: 'test@test.com', content: 'test', category: 'test').save()
    
            and:
            params.max = 5;
            def model = controller.list()
    
            expect:
            5 == model.posts.size()
            'test5' == model.posts[0].title
            'test1' == model.posts[4].title
    
        }
    
        def 'List returns the blag posts items in the next page'(){
    
            setup:
            new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test3', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test4', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test5', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test6', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test7', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test8', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test9', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test10', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test11', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test12', email: 'test@test.com', content: 'test', category: 'test').save()
    
            and:
            params.max = 5
            params.offset=5
            def model = controller.list()
    
            expect:
            model.posts
            5 == model.posts.size()
            'test10' == model.posts[0].title
            'test6' == model.posts[4].title
        }
    
        def 'List returns the blag posts items in the last page'(){
    
            setup:
            new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test3', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test4', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test5', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test6', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test7', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test8', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test9', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test10', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test11', email: 'test@test.com', content: 'test', category: 'test').save()
            new Post(title: 'test12', email: 'test@test.com', content: 'test', category: 'test').save()
    
            and:
            params.max = 5
            params.offset=10
            def model = controller.list()
    
            expect:
            model.posts
            2 == model.posts.size()
            'test12' == model.posts[0].title
            'test11' == model.posts[1].title
        }

    //There is bug in the groovy unit test code
    //see jira http://jira.grails.org/browse/GRAILS-8841
    // [ignoreCase: true] works in real application but unit tests fails
    // Issue also exists in Grails 2.2.2

//    def 'List returns the blag posts items based on dropdown category'(){
//
//        setup:
//        new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'test1').save()
//        new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'test1').save()
//        new Post(title: 'test3', email: 'test@test.com', content: 'test', category: 'test1').save()
//        new Post(title: 'test4', email: 'test@test.com', content: 'test', category: 'test1').save()
//        new Post(title: 'test5', email: 'test@test.com', content: 'test', category: 'test2').save()
//        new Post(title: 'test6', email: 'test@test.com', content: 'test', category: 'test2').save()
//        new Post(title: 'test7', email: 'test@test.com', content: 'test', category: 'test2').save()
//        new Post(title: 'test8', email: 'test@test.com', content: 'test', category: 'test2').save()
//        new Post(title: 'test9', email: 'test@test.com', content: 'test', category: 'test2').save()
//        new Post(title: 'test10', email: 'test@test.com', content: 'test', category: 'test3').save()
//        new Post(title: 'test11', email: 'test@test.com', content: 'test', category: 'test3').save()
//        new Post(title: 'test12', email: 'test@test.com', content: 'test', category: 'test3').save()
//
//        and:
//        params.categoryName='TEST3'
//        params.action='list'
//        params.controller='blog'
//        params.max=5
//        def model = controller.list()
//
//        expect:
//        model.posts
//        3 == model.posts.size()
//        'test12' == model.posts[0].title
//        'test11' == model.posts[1].title
//    }

    def 'Create adds a new blag post and redirects'() {

        setup:
            controller.params.title = 'test'
            controller.params.email = 'test@test.com'
            controller.params.content = 'test'
            controller.params.category = 'test'

        when:
            controller.createPost()
            def posts = Post.list()

        then:
            posts
            'test' == posts[0].title
            'test@test.com' == posts[0].email
            'test' == posts[0].content
            'test' == posts[0].category
            response.redirectUrl.endsWith('/list')
    }
        
    def 'Returns matching tags autoList'(){
    
    	setup:
            new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'Sport').save()
            new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'Electric').save()
    
        and:
            params.term='Spo'
            controller.autoList()
    
       	expect:
            response.text.contains("SPORT")
    }
    
    def 'Returns zero matching tags autoList'(){
    
            setup:
            new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'Sport').save()
            new Post(title: 'test2', email: 'test@test.com', content: 'test', category: 'Electric').save()
    
            and:
            params.term='gam'
            controller.autoList()
    
            expect:
            !response.text.contains('GAME')
    }

}
