import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  mik
 * @version 0.1
 */
public class SalesItemTest
{
    private SalesItem salesIte1;
    private SalesItem salesIte2;

    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        salesIte1 = new SalesItem("Java Book", 12345);
        salesIte2 = new SalesItem("Other", 123);
        salesIte2.addComment("Fred", "too expensive", 1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test that a comment can be added, and that the comment count is correct afterwards.
     */
    @Test
    public void testAddComment()
    {
        SalesItem salesIte1 = new SalesItem("Java for complete Idiots", 21998);
        assertEquals(true, salesIte1.addComment("James Duckling", "This book is great. I learned all my Java from it.", 4));
        assertEquals(1, salesIte1.getNumberOfComments());
    }

    /**
     * Test that a comment using an illegal rating value is rejected.
     */
    @Test
    public void testIllegalRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Complete Idiots, Vol 2", 19900);
        assertEquals(false, salesIte1.addComment("Joshua Black", "Not worth the money. The font is too small.", -5));
    }

    /**
     * Test that a sales item is correctly initialised (name and price).
     */
    @Test
    public void testInit()
    {
        SalesItem salesIte1 = new SalesItem("test name", 1000);
        assertEquals("test name", salesIte1.getName());
        assertEquals(1000, salesIte1.getPrice());
    }

    @Test
    public void testTwoComments()
    {
        SalesItem salesIte1 = new SalesItem("Java Book", 12345);
        assertEquals(true, salesIte1.addComment("James Duckling", "Great book. ...", 4));
        assertEquals(true, salesIte1.addComment("Fred", "Like it", 2));
        assertEquals(2, salesIte1.getNumberOfComments());
    }

    @Test
    public void testCommentFromAuthorExists()
    {
        SalesItem salesIte1 = new SalesItem("Java Book", 12345);
        assertEquals(true, salesIte1.addComment("Fred", "Like it", 2));
        assertEquals(false, salesIte1.addComment("Fred", "Changed my mind, it's great", 5));
    }

    @Test
    public void testRatingBoundaries()
    {
        SalesItem salesIte1 = new SalesItem("Java Book", 12345);
        assertEquals(false, salesIte1.addComment("James Duckling", "Great book. ...", 6));
        assertEquals(false, salesIte1.addComment("Fred", "Meh", 0));
    }


    @Test
    public void testFindMostHelpfulComment()
    {
        SalesItem salesIte1 = new SalesItem("Java Book", 12345);
        salesIte1.addComment("Fred", "Like it", 2);
        salesIte1.addComment("James Duckling", "Great book. ...", 4);
        salesIte1.upvoteComment(1);
        Comment comment1 = salesIte1.findMostHelpfulComment();
        assertNotNull(comment1);
        assertEquals(1, comment1.getVoteCount());
    }
}
