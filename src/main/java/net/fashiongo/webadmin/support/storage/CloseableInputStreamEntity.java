package net.fashiongo.webadmin.support.storage;

import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CloseableInputStreamEntity extends AbstractHttpEntity {

	private boolean isAutoCloseStream = true;

	public void setAutoCloseStream(boolean autoCloseStream) {
		isAutoCloseStream = autoCloseStream;
	}

	private final InputStream content;
	private final long length;

	/**
	 * Creates an entity with an unknown length.
	 * Equivalent to {@code new InputStreamEntity(instream, -1)}.
	 *
	 * @param instream input stream
	 * @throws IllegalArgumentException if {@code instream} is {@code null}
	 * @since 4.3
	 */
	public CloseableInputStreamEntity(final InputStream instream) {
		this(instream, -1);
	}

	/**
	 * Creates an entity with a specified content length.
	 *
	 * @param instream input stream
	 * @param length of the input stream, {@code -1} if unknown
	 * @throws IllegalArgumentException if {@code instream} is {@code null}
	 */
	public CloseableInputStreamEntity(final InputStream instream, final long length) {
		this(instream, length, null);
	}

	/**
	 * Creates an entity with a content type and unknown length.
	 * Equivalent to {@code new InputStreamEntity(instream, -1, contentType)}.
	 *
	 * @param instream input stream
	 * @param contentType content type
	 * @throws IllegalArgumentException if {@code instream} is {@code null}
	 * @since 4.3
	 */
	public CloseableInputStreamEntity(final InputStream instream, final ContentType contentType) {
		this(instream, -1, contentType);
	}

	/**
	 * @param instream input stream
	 * @param length of the input stream, {@code -1} if unknown
	 * @param contentType for specifying the {@code Content-Type} header, may be {@code null}
	 * @throws IllegalArgumentException if {@code instream} is {@code null}
	 * @since 4.2
	 */
	public CloseableInputStreamEntity(final InputStream instream, final long length, final ContentType contentType) {
		super();
		this.content = Args.notNull(instream, "Source input stream");
		this.length = length;
		if (contentType != null) {
			setContentType(contentType.toString());
		}
	}

	@Override
	public boolean isRepeatable() {
		return false;
	}

	/**
	 * @return the content length or {@code -1} if unknown
	 */
	@Override
	public long getContentLength() {
		return this.length;
	}

	@Override
	public InputStream getContent() throws IOException {
		return this.content;
	}

	/**
	 * Writes bytes from the {@code InputStream} this entity was constructed
	 * with to an {@code OutputStream}.  The content length
	 * determines how many bytes are written.  If the length is unknown ({@code -1}), the
	 * stream will be completely consumed (to the end of the stream).
	 *
	 */
	@Override
	public void writeTo(final OutputStream outstream) throws IOException {
		Args.notNull(outstream, "Output stream");
		final InputStream instream = this.content;
		try {
			final byte[] buffer = new byte[OUTPUT_BUFFER_SIZE];
			int l;
			if (this.length < 0) {
				// consume until EOF
				while ((l = instream.read(buffer)) != -1) {
					outstream.write(buffer, 0, l);
				}
			} else {
				// consume no more than length
				long remaining = this.length;
				while (remaining > 0) {
					l = instream.read(buffer, 0, (int) Math.min(OUTPUT_BUFFER_SIZE, remaining));
					if (l == -1) {
						break;
					}
					outstream.write(buffer, 0, l);
					remaining -= l;
				}
			}
		} finally {
			if(isAutoCloseStream) {
				instream.close();
			}

		}
	}

	@Override
	public boolean isStreaming() {
		return true;
	}
}
