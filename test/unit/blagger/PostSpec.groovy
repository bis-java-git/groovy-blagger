package blagger

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Post)
class PostSpec extends Specification {

    void "Test that post object is valid"() {

        when: 'the title is blank test should fail'
        def p = new Post(title: '', email: 'test@test.com', content: 'test', category: 'Sport')

        then: 'validation should fail'
        !p.validate()

        when: 'the title is less than 3 characters'
        p = new Post(title: 't1', email: 'test@test.com', content: 'test', category: 'Sport')

        then: 'validation should fail'
        !p.validate()

        when: 'the email is invalid test should fail'
        p = new Post(title: 'test1', email: 'testtest.com', content: 'test', category: 'Sport')

        then: 'validation should fail'
        !p.validate()

        when: 'the email is blank test should fail'
        p = new Post(title: 'test1', email: '', content: 'test', category: 'Sport')

        then: 'validation should fail'
        !p.validate()

        when: 'the contents is blank test should fail'
        p = new Post(title: 'test1', email: 'test@test.com', content: '', category: 'Sport')

        then: 'validation should fail'
        !p.validate()

        when: 'the category is blank test should fail'
        p = new Post(title: 'test1', email: 'test@test.com', content: 'test', category: '')

        then: 'validation should fail'
        !p.validate()

        when: 'the all data is good test should pass'
        p = new Post(title: 'test1', email: 'test@test.com', content: 'test', category: 'Sport')

        then: 'validation should pass'
        p.validate()
    }
}