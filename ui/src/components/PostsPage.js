import React, {Component} from 'react'
import PostList from './PostList'

class PostsPage extends Component {
  render(){
    return(
      <main>
        <h1>All Posts!</h1>
        <PostList/>
      </main>
    )
  }
}

export default PostsPage
