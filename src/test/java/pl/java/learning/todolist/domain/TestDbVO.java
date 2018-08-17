package pl.java.learning.todolist.domain;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestDbVO {

  public static class Existing {

    public static final Long TASK_ID = 1L;
  }

  public static class NonExisting {

    public static final Long TASK_ID = 999_999L;
  }

}
