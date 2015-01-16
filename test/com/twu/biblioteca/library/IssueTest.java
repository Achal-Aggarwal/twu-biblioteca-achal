package com.twu.biblioteca.library;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Issue;
import com.twu.biblioteca.session.User;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

public class IssueTest {
    @Test
    public void testBookIssueCreation(){
        String title = "Let Us C";
        String author = "Yashwant Kanetkar";
        String publicationDate = "2000";
        Book letusc = new Book(title,author,publicationDate);
        User achal = new User("000-0000", "achal", "", "", "");

        Issue issue = new Issue(letusc, achal);

        assertSame(letusc, issue.getIssuedItem());
        assertSame(achal, issue.getIssuer());
    }
}
