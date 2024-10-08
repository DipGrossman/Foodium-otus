package dev.shreyaspatil.foodium.data.model

import dev.shreyaspatil.foodium.model.Post
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PostTest {

    @Test
    fun testPostCreation() {
        val post = Post(1, "Test Title", "Test Author", "Test Body", "http://example.com/image.jpg")

        assertEquals(1, post.id)
        assertEquals("Test Title", post.title)
        assertEquals("Test Author", post.author)
        assertEquals("Test Body", post.body)
        assertEquals("http://example.com/image.jpg", post.imageUrl)
    }

    @Test
    fun testPostCreationWithDefaults() {
        val post = Post()

        assertEquals(0, post.id)
        assertNull(post.title)
        assertNull(post.author)
        assertNull(post.body)
        assertNull(post.imageUrl)
    }

    @Test
    fun testPostEquality() {
        val post1 = Post(1, "Title", "Author", "Body", "http://example.com/image.jpg")
        val post2 = Post(1, "Title", "Author", "Body", "http://example.com/image.jpg")
        val post3 = Post(2, "Different Title", "Different Author", "Different Body", "http://example.com/different_image.jpg")

        assertEquals(post1, post2)
        assertNotEquals(post1, post3)
    }

    @Test
    fun testPostCopy() {
        val originalPost = Post(1, "Original Title", "Original Author", "Original Body", "http://example.com/original_image.jpg")
        val copiedPost = originalPost.copy(title = "New Title")

        assertEquals(originalPost.id, copiedPost.id)
        assertEquals("New Title", copiedPost.title)
        assertEquals(originalPost.author, copiedPost.author)
        assertEquals(originalPost.body, copiedPost.body)
        assertEquals(originalPost.imageUrl, copiedPost.imageUrl)
    }

    @Test
    fun testTableName() {
        assertEquals("foodium_posts", Post.TABLE_NAME)
    }

    @Test
    fun testInvalidImageUrl() {
        val post = Post(1, "Title", "Author", "Body", "invalid_url")

        assertNotNull(post.imageUrl)
        assertFalse(isValidUrl(post.imageUrl!!))
    }

    @Test
    fun testEmptyFields() {
        val post = Post(1, "", "", "", "")

        assertEquals(1, post.id)
        assertTrue(post.title.isNullOrEmpty())
        assertTrue(post.author.isNullOrEmpty())
        assertTrue(post.body.isNullOrEmpty())
        assertTrue(post.imageUrl.isNullOrEmpty())
    }

    // Вспомогательная функция для проверки URL
    private fun isValidUrl(url: String): Boolean {
        return try {
            java.net.URL(url).toURI()
            true
        } catch (e: Exception) {
            false
        }
    }

}