//package com.bankmasr.thechampion;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vodafone.coreservice.fileexchangeadaptor.service.FileExchangeAdapterService;
//import com.vodafone.coreservice.party.exception.ResponseException;
//import com.vodafone.coreservice.party.service.OrganisationService;
//import com.vodafone.resourceorderadapter.component.dpam.service.PartyApiClientService;
//import com.vodafone.resourceorderadapter.component.dtom.OrderManagementClient;
//import com.vodafone.resourceorderadapter.component.dtom.model.DtomOrderCreate;
//import com.vodafone.resourceorderadapter.component.dtom.service.OrderManagementService;
//import com.vodafone.resourceorderadapter.component.dtom.service.OrderManagementServiceImpl;
//import com.vodafone.resourceorderadapter.config.OrganizationProperties;
//import com.vodafone.resourceorderadapter.config.PropertyConfig;
//import com.vodafone.resourceorderadapter.entity.FileJobEntity;
//import com.vodafone.resourceorderadapter.entity.GdspHierarchyResourceMappingConfigEntity;
//import com.vodafone.resourceorderadapter.exception.BadRequestException;
//import com.vodafone.resourceorderadapter.exception.ValidationException;
//import com.vodafone.resourceorderadapter.filter.RequestHeaderStore;
//import com.vodafone.resourceorderadapter.model.FileJobCreate;
//import com.vodafone.resourceorderadapter.model.FileJobRef;
//import com.vodafone.resourceorderadapter.model.JobStateType;
//import com.vodafone.resourceorderadapter.repository.FileJobRepository;
//import com.vodafone.resourceorderadapter.repository.FileJobResourceItemRepository;
//import com.vodafone.resourceorderadapter.repository.GdspHierarchyResourceMappingConfigRepository;
//import com.vodafone.resourceorderadapter.service.FileJobService;
//import com.vodafone.resourceorderadapter.service.GdspHierarchyResourceMappingConfigService;
//import com.vodafone.resourceorderadapter.util.helper.WebClientMockServer;
//import com.vodafone.resourceorderadapter.util.mapper.FileJobMapper;
//import com.vodafone.solutionhubsecurity.jwt.JwtService;
//import java.io.IOException;
//import java.time.OffsetDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
///**
// * @Author Cem Cocu
// */
//
//@SpringBootTest
//@ActiveProfiles(value = "junit")
//@ExtendWith(MockitoExtension.class)
//class SimDeviceOnboardingApiServiceImplIT implements WebClientMockServer {
//
//  private static final String AUTH_TOKEN = "Bearer 123";
//  private static final String ORGANIZATION = "organization";
//  private static final String VF_GROUP_PARTY_NAME = "exampleAdminOrganizationId";
//  private static final String NOT_VF_GROUP_PARTY_NAME = "VF-IT";
//  private static final String GDSP_CUSTOMER_ID = "gdsp";
//  private static final String GDSP_SUB_CUSTOMER_ID = "subgdsp";
//  private static final String PROFILE = "profile";
//  private static final String EMPTY = "";
//  private static final String CHANNEL = "channel";
//  private static final String CHANNEL_VALUE = "AppDirect";
//  private static final String FILE_JOB_STATUS = "acknowledged";
//  private static final String REQUEST_TYPE = "simDeviceOnboarding";
//  private static final String RANDOM_URL = "http://test.com";
//  private static final String ID = "id";
//  private static final String FILE_URI = "123";
//  private static final String WRONG_URI = "uri";
//  private static final String PARTY_FILE_PATH = "data/partyResponse.json";
//  private static final String SIM_DEVICE_ONBOARDING_CSV_PATH = "csv/simDeviceOnBoarding/simDeviceOnboarding.csv";
//  private static final String FILE_JOB_CREATE_JSON_PATH = "data/fileJobCreate.json";
//  private static final int RANDOM_INT = 5;
//
//  SimDeviceOnboardingApiServiceImpl simDeviceOnboardingApiService;
//  OrderManagementService orderManagementService;
//  FileJobEntityFactory fileJobEntityFactory;
//  GdspHierarchyResourceMappingConfigService gdspHierarchyResourceMappingConfigService;
//  FileExchangeAdapterService fileExchangeAdapterService;
//  FileJobResourceItemRepository fileJobResourceItemRepository;
//  PartyApiClientService partyApiClientService;
//  JwtService jwtService;
//  @Autowired
//  FileJobService fileJobService;
//  @Autowired
//  ObjectMapper objectMapper;
//  @Autowired
//  ModelMapper modelMapper;
//  @Autowired
//  FileJobMapper fileJobMapper;
//  @Autowired
//  OrganizationProperties organizationProperties;
//  @Autowired
//  OrganisationService organisationService;
//  @Autowired
//  FileJobRepository fileJobRepository;
//  @Mock
//  PropertyConfig propertyConfig;
//  @Mock
//  RequestHeaderStore store;
//  @Mock
//  GdspHierarchyResourceMappingConfigRepository gdspHierarchyResourceMappingConfigRepository;
//  @Mock
//  OrderManagementClient orderManagementClient;
//  @Mock
//  private RequestAttributes attrs;
//
//  @BeforeEach
//  void init() {
//    clientAndServer.reset();
//
//    attrs.setAttribute(ORGANIZATION, String.class, 1);
//    RequestContextHolder.setRequestAttributes(attrs);
//
//    fileJobService = new FileJobServiceImpl(fileJobRepository,
//        fileJobResourceItemRepository, jwtService, store,
//        organizationProperties);
//
//    partyApiClientService = new PartyApiClientService(propertyConfig, organisationService);
//    orderManagementService = new OrderManagementServiceImpl(orderManagementClient);
//    fileJobEntityFactory = new FileJobEntityFactory(modelMapper);
//    gdspHierarchyResourceMappingConfigService = new GdspHierarchyResourceMappingConfigServiceImpl(
//        gdspHierarchyResourceMappingConfigRepository);
//
//    simDeviceOnboardingApiService = new SimDeviceOnboardingApiServiceImpl(fileJobService,
//        fileJobEntityFactory,
//        orderManagementService,
//        fileExchangeAdapterService,
//        gdspHierarchyResourceMappingConfigService,
//        modelMapper, fileJobMapper,
//        organizationProperties,
//        partyApiClientService, store);
//  }
//
//  @Test
//  void test_create_with_true_payload_expect_success() throws IOException {
//    downloadFile(FILE_URI, SIM_DEVICE_ONBOARDING_CSV_PATH);
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    when(gdspHierarchyResourceMappingConfigRepository
//        .findFirstGdspHierarchyMappingForOperatorAndServiceProfile(any(), any()))
//        .thenReturn(gdspHierarchyResourceMappingConfigEntity(true));
//    when(store
//        .getHeaders())
//        .thenReturn(headers());
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    FileJobRef fileJobRef = simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN);
//    verify(store).getHeaders();
//    ArgumentCaptor<DtomOrderCreate> dtomOrderCreateArgumentCaptor = ArgumentCaptor
//        .forClass(DtomOrderCreate.class);
//    ArgumentCaptor<String> authToken = ArgumentCaptor
//        .forClass(String.class);
//    verify(orderManagementClient)
//        .sendPost(dtomOrderCreateArgumentCaptor.capture(), authToken.capture());
//    Assertions.assertEquals(JobStateType.ACKNOWLEDGED, fileJobRef.getStatus());
//  }
//
//  @Test
//  void test_create_with_true_payload_sim_partition_expect_success() throws IOException {
//    downloadFile(FILE_URI, SIM_DEVICE_ONBOARDING_CSV_PATH);
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    when(gdspHierarchyResourceMappingConfigRepository
//        .findFirstGdspHierarchyMappingForOperatorAndServiceProfile(any(), any()))
//        .thenReturn(gdspHierarchyResourceMappingConfigEntity(true));
//    when(store
//        .getHeaders())
//        .thenReturn(headers());
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobInfo().setSimPartitionChangeRequired(true);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    FileJobRef fileJobRef = simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN);
//    verify(store).getHeaders();
//    ArgumentCaptor<DtomOrderCreate> dtomOrderCreateArgumentCaptor = ArgumentCaptor
//        .forClass(DtomOrderCreate.class);
//    ArgumentCaptor<String> authToken = ArgumentCaptor
//        .forClass(String.class);
//    verify(orderManagementClient)
//        .sendPost(dtomOrderCreateArgumentCaptor.capture(), authToken.capture());
//    Assertions.assertEquals(JobStateType.ACKNOWLEDGED, fileJobRef.getStatus());
//  }
//
//  @Test
//  void test_create_with_true_payload_sim_state_change_no_gdsp_operator_configured_expect_throws_error() throws IOException {
//    downloadFile(FILE_URI, SIM_DEVICE_ONBOARDING_CSV_PATH);
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobInfo().setSimStateChangeRequired(true);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//
//    Assertions.assertThrows(ValidationException.class, () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_true_payload_caller_id_outside_from_vf_group_expect_success()
//      throws IOException {
//    downloadFile(FILE_URI, SIM_DEVICE_ONBOARDING_CSV_PATH);
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(NOT_VF_GROUP_PARTY_NAME);
//    when(gdspHierarchyResourceMappingConfigRepository
//        .findFirstGdspHierarchyMappingForOperatorAndServiceProfile(any(), any()))
//        .thenReturn(gdspHierarchyResourceMappingConfigEntity(true));
//    when(store
//        .getHeaders())
//        .thenReturn(headers());
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    FileJobRef fileJobRef = simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN);
//    verify(store).getHeaders();
//    ArgumentCaptor<DtomOrderCreate> dtomOrderCreateArgumentCaptor = ArgumentCaptor
//        .forClass(DtomOrderCreate.class);
//    ArgumentCaptor<String> authToken = ArgumentCaptor
//        .forClass(String.class);
//    verify(orderManagementClient)
//        .sendPost(dtomOrderCreateArgumentCaptor.capture(), authToken.capture());
//    Assertions.assertEquals(JobStateType.ACKNOWLEDGED, fileJobRef.getStatus());
//  }
//
//  @Test
//  void test_create_with_true_payload_when_channel_is_null_expect_success() throws IOException {
//    downloadFile(FILE_URI, SIM_DEVICE_ONBOARDING_CSV_PATH);
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(NOT_VF_GROUP_PARTY_NAME);
//    when(gdspHierarchyResourceMappingConfigRepository
//        .findFirstGdspHierarchyMappingForOperatorAndServiceProfile(any(), any()))
//        .thenReturn(gdspHierarchyResourceMappingConfigEntity(true));
//    when(store
//        .getHeaders())
//        .thenReturn(new HashMap<>());
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    FileJobRef fileJobRef = simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN);
//    verify(store).getHeaders();
//    ArgumentCaptor<DtomOrderCreate> dtomOrderCreateArgumentCaptor = ArgumentCaptor
//        .forClass(DtomOrderCreate.class);
//    ArgumentCaptor<String> authToken = ArgumentCaptor
//        .forClass(String.class);
//    verify(orderManagementClient)
//        .sendPost(dtomOrderCreateArgumentCaptor.capture(), authToken.capture());
//    Assertions.assertEquals(JobStateType.ACKNOWLEDGED, fileJobRef.getStatus());
//
//  }
//
//  @Test
//  void test_create_with_null_payload_expect_exception() {
//    Assertions.assertThrows(ValidationException.class,
//        () -> simDeviceOnboardingApiService.create(null, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_empty_gdspCustomerServiceProfile_payload_expect_exception()
//      throws IOException {
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    fileJobCreate.getJobInfo().setSimPartitionChangeRequired(true);
//    fileJobCreate.getJobInfo().setGdspCustomerServiceProfile(EMPTY);
//    Assertions.assertThrows(BadRequestException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_empty_gdspCustomerId_payload_expect_exception()
//      throws IOException {
//    when(gdspHierarchyResourceMappingConfigRepository
//        .findFirstGdspHierarchyMappingForOperatorAndServiceProfile(any(), any()))
//        .thenReturn(gdspHierarchyResourceMappingConfigEntity(false));
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    Assertions.assertThrows(ValidationException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_empty_supplierInfo_payload_expect_exception()
//      throws IOException {
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    fileJobCreate.getJobInfo().getSupplierInfo().setSupplierName(EMPTY);
//    Assertions.assertThrows(ValidationException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_not_registered_organization_payload_expect_exception()
//      throws IOException {
//    organization(PARTY_FILE_PATH, HttpStatus.NOT_FOUND);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    Assertions.assertThrows(BadRequestException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_partyService_not_available_expect_exception()
//      throws IOException {
//    organizationFail(HttpStatus.INTERNAL_SERVER_ERROR);
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    Assertions.assertThrows(ResponseException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_empty_uri_payload_expect_exception()
//      throws IOException {
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    fileJobCreate.getJobData().getFile().setUri(EMPTY);
//    Assertions.assertThrows(ValidationException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_create_with_wrong_uri_payload_expect_exception()
//      throws IOException {
//    organization(PARTY_FILE_PATH, HttpStatus.OK);
//    String s = readFromFile(FILE_JOB_CREATE_JSON_PATH);
//    FileJobCreate fileJobCreate = objectMapper.readValue(s, FileJobCreate.class);
//    fileJobCreate.getJobData().getFile().setCreationDate(OffsetDateTime.now());
//    fileJobCreate.getJobData().getFile().setUri(WRONG_URI);
//    Assertions.assertThrows(ValidationException.class,
//        () -> simDeviceOnboardingApiService.create(fileJobCreate, AUTH_TOKEN));
//  }
//
//  @Test
//  void test_getById_with_caller_from_vf_group_expect_success() {
//    FileJobEntity save = fileJobRepository.save(fileJobEntity());
//    Assertions.assertDoesNotThrow(
//        () -> simDeviceOnboardingApiService.getById(String.valueOf(save.getId())));
//  }
//
//  @Test
//  void test_getById_with_caller_not_from_vf_group_expect_success() {
//    FileJobEntity save = fileJobRepository.save(fileJobEntity());
//    Assertions.assertDoesNotThrow(
//        () -> simDeviceOnboardingApiService.getById(String.valueOf(save.getId())));
//  }
//
//  @Test
//  void test_list_with_caller_from_vf_group_expect_success() {
//    when(RequestContextHolder.currentRequestAttributes()
//        .getAttribute(organizationProperties.getFilterFieldName(), 0))
//        .thenReturn(VF_GROUP_PARTY_NAME);
//    fileJobRepository.save(fileJobEntity());
//    Assertions.assertDoesNotThrow(
//        () -> simDeviceOnboardingApiService.list(EMPTY, RANDOM_INT, RANDOM_INT, ID));
//  }
//
//  @Test
//  void test_list_with_caller_not_from_vf_group_expect_success() {
//    fileJobRepository.save(fileJobEntity());
//    Assertions.assertDoesNotThrow(
//        () -> simDeviceOnboardingApiService.list(EMPTY, RANDOM_INT, RANDOM_INT, ID));
//  }
//
//  private Optional<GdspHierarchyResourceMappingConfigEntity> gdspHierarchyResourceMappingConfigEntity(
//      boolean isGdspCustomerIdFilled) {
//    GdspHierarchyResourceMappingConfigEntity entity = new GdspHierarchyResourceMappingConfigEntity();
//    entity.setGdspCustomerId(isGdspCustomerIdFilled ? GDSP_CUSTOMER_ID : EMPTY);
//    entity.setGdspCustomerServiceProfile(PROFILE);
//    entity.setGdspSubCustomerId(isGdspCustomerIdFilled ? GDSP_SUB_CUSTOMER_ID : EMPTY);
//    return Optional.of(entity);
//  }
//
//  private Map<String, String> headers() {
//    return new HashMap<String, String>() {{
//      put(CHANNEL, CHANNEL_VALUE);
//    }};
//  }
//
//  private FileJobEntity fileJobEntity() {
//    FileJobEntity entity = new FileJobEntity();
//    entity.setStatus(FILE_JOB_STATUS);
//    entity.setFileUri(RANDOM_URL);
//    entity.setRecordCount(3);
//    entity.setSuccessCount(2);
//    entity.setRequestType(REQUEST_TYPE);
//    return entity;
//  }
//}
