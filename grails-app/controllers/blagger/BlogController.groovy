package blagger

import grails.converters.JSON

class BlogController {

    static defaultAction = "list"

    static MAX_ITEMS_IN_PAGE = 5

    static CATEGORY = "category"

    static ALL = "ALL"

    def getCategoryList(likeTerm) {
        //I am sure better ways to di it
        def criteria = Post.createCriteria()
        def categoryList = criteria.listDistinct {
            projections {
                property(CATEGORY)
            }
            ilike("category", likeTerm)
            order(CATEGORY, "asc")
        } as Set
        categoryList.collect(new LinkedHashSet<String>()) { c -> c.toUpperCase() }
    }

    def getFilterDataCount(filter) {
        def filterCriteria = Post.createCriteria();
        filterCriteria.count {
            eq('category', filter)
        }
    }

    def getFilteredData(filter, params) {
        def categoryPostsCriteria = Post.createCriteria()
        categoryPostsCriteria.list(max: params.max, offset: params.offset) {
            eq("category", filter, [ignoreCase: true])
        }
    }

    def list() {
        params.max = params.max ?: MAX_ITEMS_IN_PAGE
        def filter = params.categoryName
        def posts = Post.list(params);
        def count = Post.count

        switch (filter) {
            case null:
            case '':
            case ALL:
                break;
            default:
                posts = getFilteredData(filter, params)
                count = getFilterDataCount(filter)
        }
        model:
        [posts: posts.sort { lhs, rhs -> rhs.id <=> lhs.id }, total: count, categoryList: getCategoryList('%') << ALL]
    }

    def create() {
        [post: new Post(params)]
    }

    def createPost() {

        def aPost = new Post(params)

        if (!aPost.save(flush: true)) {
            render(view: "create", model: [post: aPost])
            return
        }

        redirect(action: defaultAction)
    }

    def autoList = {
        render getCategoryList(params.term+'%') as JSON
    }
}
