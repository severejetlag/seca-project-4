import React, {Component} from 'react'
import UserList from './UserList'
import axios from 'axios'
import Nav from './Nav'
import {Redirect} from 'react-router-dom'

class UsersPage extends Component{
  state = {
    users: []
  }

  domain = process.env.REACT_APP_SKITTLE_FLAVOR;


  async componentDidMount() {
    if(this.props.hasCurrentUser){
      try {
        const response = await axios.get(`${process.env.REACT_APP_USERS_API}/users`)
        this.setState({ users: response.data })
      } catch (error) {
        console.log('Error retrieving ideas!')
      }
    }
  }

  deleteUser = async (userId, index) => {
    try {
        await axios.delete(`${process.env.REACT_APP_USERS_API}/users/${userId}`)

        const updatedUserList = [...this.state.users]
        updatedUserList.splice(index, 1)
        this.setState({users: updatedUserList})

    } catch (error) {
        console.log(`Error deleting Idea with ID of ${userId}`)
        console.log(error)
    }
  }

  render(){
    // Redirect from Stack Overflow
    //https://stackoverflow.com/questions/43230194/how-to-use-redirect-in-the-new-react-router-dom-of-reactjs
    if(!this.props.hasCurrentUser){
      return <Redirect to='/'/>
    }
    return(
      <main id='users-container'>
        <Nav/>
        <hgroup>
          <h1>Registered Users</h1>
        </hgroup>
        <UserList currentUserName={this.props.currentUser.userName} users={this.state.users} adminUser={this.props.adminUser} deleteUser={this.deleteUser}/>
      </main>
    )
  }
}

export default UsersPage;
