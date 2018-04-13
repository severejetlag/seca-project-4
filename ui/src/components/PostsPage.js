import React, {Component} from 'react'
import PostList from './PostList'
import axios from 'axios'
import Nav from './Nav'
import {Link} from 'react-router-dom';

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

  createPost = async (post, index) => {
    try {
        const newPostResponse = await axios.post(`${process.env.REACT_APP_USERS_API}/posts/unapproved`, post)
    } catch(error) {
        console.log('Error creating new User!')
        console.log(error)
    }
  }

  deletePost = async (postId, index) => {
    try {
        await axios.delete(`${process.env.REACT_APP_USERS_API}/posts/all/${postId}`)

        const updatedPostList = [...this.state.posts]
        updatedPostList.splice(index, 1)
        this.setState({posts: updatedPostList})

    } catch (error) {
        console.log(`Error deleting post with ID of ${postId}`)
        console.log(error)
    }
  }

  toggleApproval = async (postId,index,post) => {
    try{

      post.approved = !post.approved
      await axios.put(`${process.env.REACT_APP_USERS_API}/posts/all/${postId}`, post)

      const updatedPostList = [...this.state.posts]
      updatedPostList.splice(index, 1)
      this.setState({posts: updatedPostList})

    }catch(error){
      console.log(`Error updating post with ID of ${postId}`)
      console.log(error)
    }
  }

  toggleVerified = async (postId,index,post) => {
    try{

      post.verified = !post.verified
      await axios.put(`${process.env.REACT_APP_USERS_API}/posts/all/${postId}`, post)

      const updatedPostList = [...this.state.posts]
      updatedPostList[index] = post
      this.setState({posts: updatedPostList})

    }catch(error){
      console.log(`Error updating post with ID of ${postId}`)
      console.log(error)
    }
  }

  render(){
    return(
      <main>
        <Nav/>
        <h1>Approved Posts:</h1>
        {
          this.props.adminUser ?
          <Link className='btn btn-info' to='/approvals'>Approvals Portal</Link>
          : ''
        }
        <PostList
          currentUser={this.props.currentUser}
          posts={this.state.posts}
          adminUser={this.props.adminUser}
          hasCurrentUser={this.props.hasCurrentUser}
          createPost={this.createPost}
          deletePost={this.deletePost}
          toggleApproval={this.toggleApproval}
          toggleVerified={this.toggleVerified}
        />
      </main>
    )
  }
}

export default PostsPage
