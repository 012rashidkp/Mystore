package mystore.net.Service

import mystore.net.Database.*
import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Model.Banners
import mystore.net.Requests.CreateBannerParams
import mystore.net.Requests.UpdateBannerParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class BannerServiceImpl : BannerService {
    override suspend fun CreateBanners(params: CreateBannerParams): Banners? {
        var statement: InsertStatement<Number>?=null
       dbQuery {
            statement = BannerTable.insert {
                it[title] = params.title?:""
                it[description] = params.description ?: ""
                it[banner_image]=params.banner_image?:""
            }
        }
        return statement?.resultedValues?.get(0).tobanner()
    }

    override suspend fun FindBannerbyTitle(title: String): Banners? {
        val banner= dbQuery {
            BannerTable.select { BannerTable.title.eq(title) }
                .map { it.tobanner() }.singleOrNull()
        }
        return banner




    }

    override suspend fun getAllBanners(): List<Banners?>? {
        return dbQuery {
            BannerTable.selectAll().map { it.tobanner() }
        }
    }

    override suspend fun findBannerById(banner_id: Int): Banners? {
        val banner= dbQuery {
            BannerTable.select { BannerTable.banner_id.eq(banner_id) }
                .map { it.tobanner() }.singleOrNull()
        }
        return banner
    }

    override suspend fun deleteBanner(banner_id: Int): Boolean {
        return dbQuery {
            CategoryTable.deleteWhere { BannerTable.banner_id eq  banner_id } > 0
        }
    }

    override suspend fun updateBanner(params: UpdateBannerParams): Boolean {
        var result = -1
        dbQuery {
            result = BannerTable.update({ BannerTable.banner_id eq params.banner_id}) {
                it[title] = params.title?:""
                it[description] = params.description?:""
                it[banner_image]=params.banner_image?:""
            }
        }
        return result == 1
    }

}