package com.mall.jiuzhenbao.common;

import java.util.List;
import java.util.UUID;

public interface ICommonService {
	/**
	 * This method is used to get organization for specify resource.
	 * The return organization id will be used for permission check.
	 * @param resourceId
	 * @return
	 */
	default UUID getOrganizationIdForPermissionCheck(UUID resourceId) {
		throw new RuntimeException("This method is not implement!!");
	};
}
