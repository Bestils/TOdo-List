package pl.java.learning.todolist.domain;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestDbVO {

  public static class Existing {

    public static final Long TASK_ID = 1L;
    public static final Long CATEGORY_ID = 1L;
    public static final String CATEGORY_NAME = "Uncategorized";
  }

  public static class NonExisting {

    public static final Long TASK_ID = 999_999L;
    public static final Long CATEGORY_ID = 999_999L;
  }

}
