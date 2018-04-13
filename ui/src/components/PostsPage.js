import React, {Component} from 'react'
import PostList from './PostList'
import axios from 'axios'
import Nav from './Nav'

class PostsPage extends Component {
  state = {
    posts:[]
  }
  async componentDidMount() {
    try {
      const response = await axios.get(`${process.env.REACT_APP_USERS_API}/posts/approved`)
      this.setState({ posts: response.data })
    } catch (error) {
      console.log('Error retrieving posts!')
    }
  }

  render(){
    return(
      <main>
        <Nav/>

        <h1>Approved Posts:</h1>
        <PostList posts={this.state.posts} adminUser={this.props.adminUser}/>
      </main>
    )
  }
}

export default PostsPage
