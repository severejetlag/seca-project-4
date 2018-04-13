import React from 'react'
import Post from './Post'

const PostList = props => {
  const PostComponents = props.posts.map((post, index) => {
    return (
      <Post
        adminUser={props.adminUser}
        post={post}
        key={index}
        index={index}
      />
    )
  })
  return(
    <ul>
      {PostComponents}
    </ul>
  )
}


export default PostList
