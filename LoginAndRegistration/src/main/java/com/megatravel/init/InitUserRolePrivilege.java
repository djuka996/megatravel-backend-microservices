//package com.megatravel.init;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.megatravel.model.system_user_info.Privilege;
//import com.megatravel.model.system_user_info.Role;
//import com.megatravel.model.system_user_info.User;
//import com.megatravel.repository.PrivilegeRepository;
//import com.megatravel.repository.RoleRepository;
//import com.megatravel.repository.UserRepository;
//
//@Component
//public class InitUserRolePrivilege{
//
//	@Autowired
//	private PrivilegeRepository privilegeRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	/*
//	 * @Autowired private CheckPassword checkPassword;
//	 */
//
//	@PostConstruct
//	public void start() {
//
//		// create privileges
//		// user controller
//		Privilege getAllUsers = new Privilege(1L, "getAllUsers");
//		Privilege getUser = new Privilege(2L, "getUser");
//		Privilege getUserByEmail = new Privilege(3L, "getUserByEmail");
//		Privilege addRoleToUser = new Privilege(4L, "addRoleToUser");
//		Privilege deleteRoleFromUser = new Privilege(5L, "deleteRoleFromUser");
//
//		// role controller
//		Privilege getAllRoles = new Privilege(6L, "getAllRoles");
//		Privilege getRole = new Privilege(7L, "getRole");
//		Privilege createRole = new Privilege(8L, "createRole");
//		Privilege updateRole = new Privilege(9L, "updateRole");
//		Privilege deleteRole = new Privilege(10L, "deleteRole");
//		Privilege addPrivilegeToRole = new Privilege(11L, "addPrivilegeToRole");
//		Privilege deletePrivilegeFromRole = new Privilege(12L, "deletePrivilegeFromRole");
//
//		// privilege controller
//		Privilege getAllPrivileges = new Privilege(13L, "getAllPrivileges");
//		Privilege getPrivilege = new Privilege(14L, "getPrivilege");
//		Privilege createPrivilege = new Privilege(15L, "createPrivilege");
//		Privilege updatePrivilege = new Privilege(16L, "updatePrivilege");
//		Privilege deletePrivilege = new Privilege(17L, "deletePrivilege");
//		
//		//Message controller
//		Privilege getInbox = new Privilege(18L, "getInbox");
//		Privilege getChat = new Privilege(19L, "getChat");
//		Privilege sendMessage = new Privilege(20L, "sendMessage");
//		Privilege markReadChat = new Privilege(21L, "markReadChat");
//		
//		//Rating controller
//		Privilege getReview = new Privilege(22L, "getReview");
//		Privilege getUserReviews = new Privilege(23L, "getUserReviews");
//		Privilege getUnreviewedReviews = new Privilege(24L, "getUnreviewedReviews");
//		Privilege getReviewsForRoom = new Privilege(25L, "getReviewsForRoom");
//		Privilege createReview = new Privilege(26L, "createReview");
//		Privilege updateReview = new Privilege(27L, "updateReview");
//		Privilege deleteReview = new Privilege(28L, "deleteReview");
//		Privilege getChatRating = new Privilege(29L, "getChatRating");
//		
//		//AccommodationTypeController
//		Privilege getRoomType = new Privilege(30L, "getRoomType");
//		Privilege createAccommodationType = new Privilege(31L, "createAccommodationType");
//		Privilege updateAccommodationType = new Privilege(32L, "updateAccommodationType");
//		Privilege removeAccommodationType = new Privilege(33L, "removeAccommodationType");
//		
//		//AddressController
//		Privilege getHotelsAddress = new Privilege(34L, "getHotelsAddress");
//		Privilege createAddress = new Privilege(35L, "createAddress");
//		Privilege updateAddress = new Privilege(36L, "updateAddress");
//		Privilege removeAddress = new Privilege(37L, "removeAddress");
//		
//		//ExtraOptionController
//		Privilege getRoomExtraOptionsWithHotelId = new Privilege(38L, "getRoomExtraOptionsWithHotelId");
//		Privilege getRoomExtraOptionsWithRoomId = new Privilege(39L, "getRoomExtraOptionsWithRoomId");
//		Privilege getRoomExtraOption = new Privilege(40L, "getRoomExtraOption");
//		Privilege createExtraOption = new Privilege(41L, "createExtraOption");
//		Privilege updateRoom = new Privilege(42L, "updateRoom");
//		Privilege removeRoom = new Privilege(43L, "removeRoom");
//		
//		//HotelController
//		Privilege getAllHotels = new Privilege(44L, "getAllHotels");
//		Privilege getHotel = new Privilege(45L, "getHotel");
//		Privilege createHotel = new Privilege(46L, "createHotel");
//		Privilege updateHotel = new Privilege(47L, "updateHotel");
//		Privilege removeHotel = new Privilege(48L, "removeHotel");
//		
//		//ReservationController
//		Privilege getAllReservations = new Privilege(49L, "getAllReservations");
//		Privilege getAllReservationsForUser = new Privilege(50L, "getAllReservationsForUser");
//		Privilege getReservation = new Privilege(51L, "getReservation");
//		Privilege getRoomReservations = new Privilege(52L, "getRoomReservations");
//		Privilege getHotelReservations = new Privilege(53L, "getHotelReservations");
//		Privilege createReservation = new Privilege(54L, "createReservation");
//		Privilege updateReservation = new Privilege(55L, "updateReservation");
//		Privilege removeReservation = new Privilege(56L, "removeReservation");
//		
//		//RoomController
//		Privilege getHotelRooms = new Privilege(57L, "getHotelRooms");
//		Privilege getHotelRoom = new Privilege(58L, "getHotelRoom");
//		Privilege createRoom = new Privilege(59L, "createRoom");
//		Privilege updateRoomRoom = new Privilege(60L, "updateRoomRoom");
//		Privilege removeRoomRoom = new Privilege(61L, "removeRoomRoom");
//		Privilege updateRating = new Privilege(62L, "updateRating");
//		
//		// save privileges
//		privilegeRepository.save(getAllUsers);
//		privilegeRepository.save(getUser);
//		privilegeRepository.save(getUserByEmail);
//		privilegeRepository.save(addRoleToUser);
//		privilegeRepository.save(deleteRoleFromUser);
//		
//		privilegeRepository.save(getAllRoles);
//		privilegeRepository.save(getRole);
//		privilegeRepository.save(createRole);
//		privilegeRepository.save(updateRole);
//		privilegeRepository.save(deleteRole);
//		privilegeRepository.save(addPrivilegeToRole);
//		privilegeRepository.save(deletePrivilegeFromRole);
//		
//		privilegeRepository.save(getAllPrivileges);
//		privilegeRepository.save(getPrivilege);
//		privilegeRepository.save(createPrivilege);
//		privilegeRepository.save(updatePrivilege);
//		privilegeRepository.save(deletePrivilege);
//		
//		privilegeRepository.save(getInbox);
//		privilegeRepository.save(getChat);
//		privilegeRepository.save(sendMessage);
//		privilegeRepository.save(markReadChat);
//		
//		privilegeRepository.save(getReview);
//		privilegeRepository.save(getUserReviews);
//		privilegeRepository.save(getUnreviewedReviews);
//		privilegeRepository.save(getReviewsForRoom);
//		privilegeRepository.save(createReview);
//		privilegeRepository.save(updateReview);
//		privilegeRepository.save(deleteReview);
//		privilegeRepository.save(getChatRating);
//		
//		privilegeRepository.save(getRoomType);
//		privilegeRepository.save(createAccommodationType);
//		privilegeRepository.save(updateAccommodationType);
//		privilegeRepository.save(removeAccommodationType);
//		
//		privilegeRepository.save(getHotelsAddress);
//		privilegeRepository.save(createAddress);
//		privilegeRepository.save(updateAddress);
//		privilegeRepository.save(removeAddress);
//		
//		privilegeRepository.save(getRoomExtraOptionsWithHotelId);
//		privilegeRepository.save(getRoomExtraOptionsWithRoomId);
//		privilegeRepository.save(getRoomExtraOption);
//		privilegeRepository.save(createExtraOption);
//		privilegeRepository.save(updateRoom);
//		privilegeRepository.save(removeRoom);
//		
//		privilegeRepository.save(getAllHotels);
//		privilegeRepository.save(getHotel);
//		privilegeRepository.save(createHotel);
//		privilegeRepository.save(updateHotel);
//		privilegeRepository.save(removeHotel);
//		
//		privilegeRepository.save(getAllReservations);
//		privilegeRepository.save(getAllReservationsForUser);
//		privilegeRepository.save(getReservation);
//		privilegeRepository.save(getRoomReservations);
//		privilegeRepository.save(getHotelReservations);
//		privilegeRepository.save(createReservation);
//		privilegeRepository.save(updateReservation);
//		privilegeRepository.save(removeReservation);
//		
//		privilegeRepository.save(getHotelRooms);
//		privilegeRepository.save(getHotelRoom);
//		privilegeRepository.save(createRoom);
//		privilegeRepository.save(updateRoomRoom);
//		privilegeRepository.save(removeRoomRoom);
//		privilegeRepository.save(updateRating);
//
//		// create roles
//		// role USER
//		Role roleRegistered = new Role(1L, "ROLE_LOGGED");
//		Set<Privilege> tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getUser);
//		tempPriviliges1.add(getUserByEmail);
//		tempPriviliges1.add(getInbox);
//		tempPriviliges1.add(getChat);
//		tempPriviliges1.add(sendMessage);
//		tempPriviliges1.add(markReadChat);
//		tempPriviliges1.add(getReview);
//		tempPriviliges1.add(getUserReviews);
//		tempPriviliges1.add(getReviewsForRoom);
//		tempPriviliges1.add(createReview);
//		tempPriviliges1.add(updateReview);
//		tempPriviliges1.add(getChatRating);
//		tempPriviliges1.add(getRoomType);
//		tempPriviliges1.add(getHotelsAddress);
//		tempPriviliges1.add(getRoomExtraOptionsWithHotelId);
//		tempPriviliges1.add(getRoomExtraOptionsWithRoomId);
//		tempPriviliges1.add(getRoomExtraOption);		
//		tempPriviliges1.add(getAllHotels);
//		tempPriviliges1.add(getHotel);
//		tempPriviliges1.add(getAllReservationsForUser);
//		tempPriviliges1.add(getReservation);
//		tempPriviliges1.add(getRoomReservations);
//		tempPriviliges1.add(getHotelRooms);
//		tempPriviliges1.add(getHotelRoom);
//		
//		//dodati privilegije
//		roleRegistered.setPrivileges(tempPriviliges1);
//		roleRepository.save(roleRegistered);
//
//		Role roleAdmin = new Role(2L, "ROLE_ADMIN");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllUsers);
//		tempPriviliges1.add(getUser);
//		tempPriviliges1.add(getUserByEmail);
//		tempPriviliges1.add(addRoleToUser);
//		tempPriviliges1.add(deleteRoleFromUser);
//		tempPriviliges1.add(getAllRoles);
//		tempPriviliges1.add(getRole);
//		tempPriviliges1.add(createRole);
//		tempPriviliges1.add(updateRole);
//		tempPriviliges1.add(deleteRole);
//		tempPriviliges1.add(addPrivilegeToRole);
//		tempPriviliges1.add(deletePrivilegeFromRole);
//		tempPriviliges1.add(getAllPrivileges);
//		tempPriviliges1.add(getPrivilege);
//		tempPriviliges1.add(createPrivilege);
//		tempPriviliges1.add(updatePrivilege);
//		tempPriviliges1.add(deletePrivilege);
//		tempPriviliges1.add(getInbox);
//		tempPriviliges1.add(getChat);
//		tempPriviliges1.add(sendMessage);
//		tempPriviliges1.add(markReadChat);
//		tempPriviliges1.add(getReview);
//		tempPriviliges1.add(getUserReviews);
//		tempPriviliges1.add(getUnreviewedReviews);
//		tempPriviliges1.add(getReviewsForRoom);
//		tempPriviliges1.add(createReview);
//		tempPriviliges1.add(updateReview);
//		tempPriviliges1.add(deleteReview);
//		tempPriviliges1.add(getChatRating);
//		tempPriviliges1.add(getRoomType);
//		tempPriviliges1.add(createAccommodationType);
//		tempPriviliges1.add(updateAccommodationType);
//		tempPriviliges1.add(removeAccommodationType);
//		tempPriviliges1.add(getHotelsAddress);
//		tempPriviliges1.add(createAddress);
//		tempPriviliges1.add(updateAddress);
//		tempPriviliges1.add(removeAddress);
//		tempPriviliges1.add(getRoomExtraOptionsWithHotelId);
//		tempPriviliges1.add(getRoomExtraOptionsWithRoomId);
//		tempPriviliges1.add(getRoomExtraOption);
//		tempPriviliges1.add(createExtraOption);
//		tempPriviliges1.add(updateRoom);
//		tempPriviliges1.add(removeRoom);
//		tempPriviliges1.add(getAllHotels);
//		tempPriviliges1.add(getHotel);
//		tempPriviliges1.add(createHotel);
//		tempPriviliges1.add(updateHotel);
//		tempPriviliges1.add(removeHotel);
//		tempPriviliges1.add(getAllReservations);
//		tempPriviliges1.add(getAllReservationsForUser);
//		tempPriviliges1.add(getReservation);
//		tempPriviliges1.add(getRoomReservations);
//		tempPriviliges1.add(getHotelReservations);
//		tempPriviliges1.add(createReservation);
//		tempPriviliges1.add(updateReservation);
//		tempPriviliges1.add(removeReservation);
//		tempPriviliges1.add(getHotelRooms);
//		tempPriviliges1.add(getHotelRoom);
//		tempPriviliges1.add(createRoom);
//		tempPriviliges1.add(updateRoomRoom);
//		tempPriviliges1.add(removeRoomRoom);
//		tempPriviliges1.add(updateRating);
//		//dodati privilegije
//		roleAdmin.setPrivileges(tempPriviliges1);
//		roleRepository.save(roleAdmin);
//
//		// role ROLE
//		Role roleAgent = new Role(3L, "ROLE_AGENT");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getUser);
//		tempPriviliges1.add(getUserByEmail);
//		tempPriviliges1.add(getInbox);
//		tempPriviliges1.add(getChat);
//		tempPriviliges1.add(sendMessage);
//		tempPriviliges1.add(markReadChat);
//		tempPriviliges1.add(getReview);
//		tempPriviliges1.add(getUserReviews);
//		tempPriviliges1.add(getUnreviewedReviews);
//		tempPriviliges1.add(getReviewsForRoom);
//		tempPriviliges1.add(deleteReview);
//		tempPriviliges1.add(getChatRating);	
//		tempPriviliges1.add(getRoomType);
//		tempPriviliges1.add(createAccommodationType);
//		tempPriviliges1.add(updateAccommodationType);
//		tempPriviliges1.add(removeAccommodationType);
//		tempPriviliges1.add(getHotelsAddress);
//		tempPriviliges1.add(createAddress);
//		tempPriviliges1.add(updateAddress);
//		tempPriviliges1.add(removeAddress);
//		tempPriviliges1.add(getRoomExtraOptionsWithHotelId);
//		tempPriviliges1.add(getRoomExtraOptionsWithRoomId);
//		tempPriviliges1.add(getRoomExtraOption);
//		tempPriviliges1.add(createExtraOption);
//		tempPriviliges1.add(updateRoom);
//		tempPriviliges1.add(removeRoom);		
//		tempPriviliges1.add(getAllHotels);
//		tempPriviliges1.add(getHotel);
//		tempPriviliges1.add(getAllReservations);
//		tempPriviliges1.add(getReservation);
//		tempPriviliges1.add(getRoomReservations);
//		tempPriviliges1.add(getHotelReservations);
//		tempPriviliges1.add(createReservation);
//		tempPriviliges1.add(updateReservation);
//		tempPriviliges1.add(removeReservation);
//		tempPriviliges1.add(getHotelRooms);
//		tempPriviliges1.add(getHotelRoom);
//		tempPriviliges1.add(createRoom);
//		tempPriviliges1.add(updateRoomRoom);
//		tempPriviliges1.add(removeRoomRoom);
//		tempPriviliges1.add(updateRating);
//		//dodati privilegije
//		roleAgent.setPrivileges(tempPriviliges1);
//		roleRepository.save(roleAgent);
//
//
//
//		// create users
//		// sifra = 123
//		User userAll = new User(1L, "stefan", "bokic", "AjLAcUiNLnUfi0H4yXbrE9/PqCQAerWP",
//				"oSo0UHjkPRZL4qE0WfJVEQ==", "s.bokic@yahoo.com");
//		Set<Role> tempRoles1 = new HashSet<Role>();
//		tempRoles1.add(roleAdmin);
//		userAll.setRoles(tempRoles1);
//		userRepository.save(userAll);
//
//		// sifra = andrija
//		User userDeleteUpdate = new User(2L, "andrija", "cvejic", "Qa5mzjfzpusizh3JURiBDjbsNjOIleYq",
//				"66x2x3KZNB0MF6YRc5XIYw==", "andrija@gmail.com");
//		Set<Role> tempRoles2 = new HashSet<Role>();
//		tempRoles2.add(roleRegistered);
//		userDeleteUpdate.setRoles(tempRoles2);
//		userRepository.save(userDeleteUpdate);
//
//		// sifra = 123
//		User userReadWrite = new User(3L, "Katarina-Glorija", "Grujic", "AjLAcUiNLnUfi0H4yXbrE9/PqCQAerWP",
//				"oSo0UHjkPRZL4qE0WfJVEQ==", "katarina@gmail.com");
//		Set<Role> tempRoles3 = new HashSet<Role>();
//		tempRoles3.add(roleAgent);
//		userReadWrite.setRoles(tempRoles3);
//		userRepository.save(userReadWrite);
//
//	}
//}
