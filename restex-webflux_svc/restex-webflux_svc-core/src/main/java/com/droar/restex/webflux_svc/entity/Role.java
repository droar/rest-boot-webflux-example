package com.droar.restex.webflux_svc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.droar.boot.fwk.base.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Example Role class for security purposes.
 *
 * @author droar
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "exm_roles")
public class Role extends AbstractEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  

  /** The role id. */
  @Id
  @Column(name = "rle_id")
  private int roleId;

  /** The name. */
  @Column(name = "rle_name")
  private String name;

}
