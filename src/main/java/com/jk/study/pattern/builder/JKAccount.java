package com.jk.study.pattern.builder;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

// 편의상 Data 사용
@Data
@Builder(builderMethodName = "accountBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class JKAccount {
  // 필수 값
  private String id;
  private String password;

  // 비 필수값
  private String comment;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  public static JKAccountBuilder builder(
      String id,
      String password) {

    if (!StringUtils.hasText(id) || !StringUtils.hasText(password)) {
      throw new NullPointerException();
    }

    return accountBuilder()
        .id(id)
        .password(password)
        .createdDate(LocalDateTime.now());
  }
}
