package factory

import com.marko.data.entities.ProjectEntity
import com.marko.remote.entities.ApiResponse
import com.marko.remote.entities.OwnerRemote
import com.marko.remote.entities.ProjectRemote

object ProjectFactoryRemote {

	private val owner: OwnerRemote
	get() = OwnerRemote(
			ownerName = DataFactoryRemote.string,
			avatar = DataFactoryRemote.string
	)

	val projectRemote: ProjectRemote
	get() = ProjectRemote(
			id = DataFactoryRemote.string,
			projectName = DataFactoryRemote.string,
			projectFullName = DataFactoryRemote.string,
			starCount = DataFactoryRemote.long,
			owner = this.owner,
			dateCreated = DataFactoryRemote.string
	)

	val apiResponse: ApiResponse
	get() = ApiResponse(
			projects = listOf(projectRemote, projectRemote)
	)

	val projectEntity: ProjectEntity
	get() = ProjectEntity(
			id = DataFactoryRemote.string,
			name = DataFactoryRemote.string,
			fullName = DataFactoryRemote.string,
			starCount = DataFactoryRemote.string,
			ownerName = DataFactoryRemote.string,
			ownerAvatar = DataFactoryRemote.string,
			dateCreated = DataFactoryRemote.string
	)
}