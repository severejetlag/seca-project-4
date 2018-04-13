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
    <ul className='list-group list-group-flush' id='post-list'>
      {PostComponents}
    </ul>
  )
}


export default PostList
