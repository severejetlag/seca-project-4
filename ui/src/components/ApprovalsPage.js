import React, {Component} from 'react'
import PostList from './PostList'
import axios from 'axios'
import Nav from './Nav'
import {Redirect} from 'react-router-dom'


class ApprovalsPage extends Component{
  state = {
    posts:[],
    isApprovalPage: true
  }
  async componentDidMount() {
    try {
      const response = await axios.get(`${process.env.REACT_APP_USERS_API}/posts/unapproved`)
      this.setState({ posts: response.data })
    } catch (error) {
      console.log('Error retrieving posts!')
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

  render(){
    if(!this.props.adminUser){
      return <Redirect to='/login'/>
    }
    return(
      <main id='approvals-container'>
        <Nav/>
        <h1>Manage Post Approvals</h1>
        <PostList
          posts={this.state.posts}
          adminUser={this.props.adminUser}
          hasCurrentUser={this.props.hasCurrentUser}
          deletePost={this.deletePost}
          toggleApproval={this.toggleApproval}
          currentUser={this.props.currentUser}
          isApprovalPage={this.state.isApprovalPage}
        />
      </main>
    )
  }
}

export default ApprovalsPage
