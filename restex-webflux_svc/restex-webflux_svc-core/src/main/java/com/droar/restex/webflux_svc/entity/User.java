package com.droar.restex.webflux_svc.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.droar.boot.fwk.base.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Example user class.
 *
 * @author droar
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "exm_users")
public class User extends AbstractEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7057285610553691773L;

  /** The id. */
  @Id
  @Access(value = AccessType.PROPERTY)
  @Column(nullable = false, unique = true, name = "usr_id")
  @SequenceGenerator(name = "usrKey_generator", sequenceName = "public.seq_id_user", allocationSize = 1)
  @GeneratedValue(generator = "usrKey_generator", strategy = GenerationType.SEQUENCE)
  private Integer id;

  /** The first name. */
  @Column(name = "usr_first_name")
  private String firstName;

  /** The last name. */
  @Column(name = "usr_last_name")
  private String lastName;
  

  /** The career. */
  @Column(name = "usr_career")
  private String career;
  
  /** The username. */
  @Column(name = "usr_username")
  private String username;
  
  /** The password. */
  @Column(name = "usr_pwd")
  private String password;
  
  /** The role. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usr_role_id", referencedColumnName = "rle_id")
  private Role role;
}
