import React from 'react'
import Post from './Post'
import PostNewForm from './PostNewForm'

const PostList = props => {
  const PostComponents = props.posts.map((post, index) => {
    return (
      <Post
        adminUser={props.adminUser}
        deletePost={props.deletePost}
        post={post}
        key={index}
        index={index}
      />
    )
  })
  return(
    <ul className='list-group list-group-flush' id='post-list'>
      {
        props.isApprovalPage ?
        <li className='post list-group-item'>
          <PostNewForm
            currentUser={props.currentUser}
            hasCurrentUser={props.hasCurrentUser}
            createPost={props.createPost}
          />
        </li>
        :
        ''
      }
      {PostComponents}
    </ul>
  )
}


export default PostList
